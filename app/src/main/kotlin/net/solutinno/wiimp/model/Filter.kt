package net.solutinno.wiimp.model

data class Filter (
        var groupBy : Int = 0,
        var orderBy : Int = 0,
        var dateFrom : Long = -1,
        var dateTo : Long = -1,
        var length : Int = 0,
        var favorites : Int = 0,
        var sites : Array<String> = arrayOf(),
        var tags : Array<String> = arrayOf()
)
