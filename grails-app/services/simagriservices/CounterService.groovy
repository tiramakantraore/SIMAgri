package simagriservices

import grails.transaction.Transactional
import simagri.Counter

import static java.util.Calendar.*
@Transactional
class CounterService {
  /**
   * Increments a counter
   */
  def increment(Map params) {
    if (params.name) {
      Counter counter = new Counter(name: params.name ?: '',user:params.user?:'',connected:params.connected?:null)
      counter.save()
      if (params.dateCreated) {
        counter.dateCreated = params.dateCreated
        counter.save()
      }
    }
  }
  
  /**
   * List names of all counters
   */
  def getCounterNames() {
    Counter.executeQuery("select distinct name from Counter")
  }

  /**
   * List all names with total counts.
   */
  def getCounterCounts() {
    Counter.executeQuery("select name, count(name) from Counter group by name")
  }
    def getCounterByMonth(Map params) {


        if (!params.from && !params.to && !params.month) {
            params.month = new Date()
        }

        if (params.from as Boolean != params.to as Boolean) throw new IllegalArgumentException("'From' without 'To'")

        if (params.month) {
            Date month = params.month

            month.clearTime() // Uhrzeit auf 00:00 setzen
            month[DATE] = 1 // Der erste Tag des angegebenen Monats
            params.from = month.clone()

            month[MONTH] += 1 // N�chster Monat um 00:00
            month[SECOND] -= 1
            params.to = month.clone()
        }

        if (params.to < params.from) throw new IllegalArgumentException("'To' is lower than 'From'")


       return Counter.executeQuery("select count(name) from Counter where dateCreated>=:dateDebut and dateCreated<=:dateFin",[dateDebut:params.from,dateFin:params.to])
    }
  def getCounterData(Map params) {
    if (!params.name)  throw new IllegalArgumentException("'params.name' missing")
    
    if (!params.from && !params.to && !params.month) {
      params.month = new Date()
    }
    
    if (params.from as Boolean != params.to as Boolean) throw new IllegalArgumentException("'From' without 'To'")
    
    if (params.month) {
      Date month = params.month
      
      month.clearTime() // Uhrzeit auf 00:00 setzen
      month[DATE] = 1 // Der erste Tag des angegebenen Monats
      params.from = month.clone()
      
      month[MONTH] += 1 // N�chster Monat um 00:00
      month[SECOND] -= 1
      params.to = month.clone()
    }
    
    if (params.to < params.from) throw new IllegalArgumentException("'To' is lower than 'From'")
    
    def c = Counter.createCriteria()
    def counterList = c.list {
      eq("name", params.name)
      between("dateCreated", params.from, params.to)
    }
    
    List statList = [0] * ((params.to - params.from) + 1) // Komplette Ergebnisliste mit '0' initialisieren
    counterList.each { statList[it.dateCreated - params.from] += 1 }
    return statList
  }
  
  def deleteCounter(params) {
    if (!params.name)  throw new IllegalArgumentException("'params.name' missing")
    Counter.executeUpdate("DELETE Counter c where c.name = :oldName", [oldName: params.name])
  }
}
