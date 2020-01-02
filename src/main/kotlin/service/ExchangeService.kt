package service

import entity.ExchangeEntry
import khttp.get
import org.json.JSONObject
import repository.ExchangeEntryRepository

interface ExchangeService {
    fun exchange(exchangeEntry: ExchangeEntry) : ExchangeEntry
//    fun getRates() : Map<String, BigDecimal>
}


class ExchangeServiceImpl (private val exchangeEntryRepository: ExchangeEntryRepository) : ExchangeService {

    private val exchangeApiUrl  = "https://api.exchangeratesapi.io/latest?base=USD"
    private val converter = { amount: Double, rate: Double -> amount * rate }

    override fun exchange(exchangeEntry: ExchangeEntry) : ExchangeEntry {

        val rates = getRates()
        val srcRate = getCurrencyRate(exchangeEntry.srcCurrency, rates)
        val usdAmount =  converter(exchangeEntry.srcAmount, srcRate)
        val dstRate = getCurrencyRate(exchangeEntry.dstCurency, rates)
        val dstAmount = converter(usdAmount, dstRate)
        exchangeEntry.dstAmount = dstAmount

        return exchangeEntryRepository.insert(exchangeEntry)

    }

    fun getRates() : JSONObject{
        return get(exchangeApiUrl)
            .jsonObject
            .getJSONObject("rates")
    }

    fun getCurrencyRate(currency: String) : Double{
        return getCurrencyRate(currency, null)
    }

    fun getCurrencyRate(currency: String, rates: JSONObject?) : Double {
        rates?.let {
            return rates.getDouble(currency)
        }
        return  getRates().getDouble(currency)
    }
}