package repository

import entity.ExchangeEntry
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.joda.time.DateTime

object ExchangeEntries : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val user = varchar("user", 200)
    val srcCurrency = varchar("src_currency", 3)
    val srcAmount = decimal("src_amount", 10, 2)
    val dstCurrency =  varchar("dst_currency", 3)
    val rate = decimal("rate", 18, 10)
    val created_at = datetime("created_at")
}


class ExchangeEntryRepository : Repository<ExchangeEntry> {

    override fun insert (entry : ExchangeEntry) : ExchangeEntry{
        ExchangeEntries.insert {
            it[user] = entry.user
            it[srcCurrency] = entry.srcCurrency
            it[srcAmount] = entry.srcAmount.toBigDecimal()
            it[dstCurrency] = entry.dstCurency
            it[rate] = entry.rate.toBigDecimal()
            it[created_at] = DateTime(entry.created_at)
        }
        return entry
    }


    /**
     * Update an issue
     */
    override fun update (entry : ExchangeEntry){
//        Issues.update({Issues.number.eq(issue.number)}) {
//            it[number] = issue.number
//            it[title] = issue.title
//            it[created_at] = DateTime(issue.created_at)
//        }
    }

}
