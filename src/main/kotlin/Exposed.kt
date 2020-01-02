
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import repository.ExchangeEntries


fun initDb() {
    //Database.connect("jdbc:h2:file:~/h2/octoeventsdb", driver = "org.h2.Driver")
    LoggerFactory.getLogger("EXPOSED").info("creating database")
    Database.connect("jdbc:h2:mem:octoeventsdb;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(ExchangeEntries)
    }

}

