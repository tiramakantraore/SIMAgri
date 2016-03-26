import org.hibernate.dialect.MySQL5InnoDBDialect

//dataSource {
//    pooled = true
//    jmxExport = true
//    driverClassName = "org.h2.Driver"
//    username = "sa"
//    password = ""
//}
hibernate {
    cache.use_second_level_cache = false
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
  //  singleSession = true // configure OSIV singleSession mode
}
grails {
    mongo {
        databaseName = "db"
    }
}
mongodb {
    host = 'localhost'
    port = 27017
    databaseName = 'test'
}
// environment specific settings
environments {

    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    development {
        dataSource {
            dialect = MySQL5InnoDBDialect
            driverClassName = "com.mysql.jdbc.Driver"
            username = "root"
            password = "root"
            url = "jdbc:mysql://localhost:3306/simagriburkina"
            dbCreate = "update"
        }

    }


    }
}
