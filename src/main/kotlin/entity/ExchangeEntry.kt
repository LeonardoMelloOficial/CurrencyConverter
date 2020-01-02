package entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*


@JsonIgnoreProperties(ignoreUnknown = true)

data class ExchangeEntry(
    var id : Int?,
    var user: String,
    var srcCurrency: String,
    var srcAmount: Double,
    var dstCurency: String,
    var dstAmount: Double,
    var rate: Float,
    @JsonFormat(timezone = "UTC")
    var created_at: Date)


