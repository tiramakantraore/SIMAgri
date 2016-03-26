package simagriclasses

/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 10/09/12
 * Time: 02:51
 * To change this template use File | Settings | File Templates.
 */
class MyGroovyUtils {
   def static getMonthName(Integer month){
        switch (month){
            case 1: 'Jan'
                   break
            case 2: 'Fev'
                break
            case 3: 'Mar'
                break
            case 4: 'Avr'
                break
            case 5: 'Mai'
                break
            case 6: 'Juin'
                break
            case 7: 'Juil'
                break
            case 8: 'Aoû'
                break
            case 9: 'Sep'
                break
            case 10: 'Oct'
                break
            case 11: 'Nov'
                break
            case 12: 'Dec'
                break
            default:''
                break;
        }
    }

    def static getLongQuarter(BigDecimal month){
        switch (month){
            case 1: '1er Trimestre'
                break
            case 2: '2è Trimestre'
                break
            case 3: '3è Trimestre'
                break
            case 4: '4è Trimestre'
                break
            default:''
                break;

        }
    }
    def static getWeekName(Integer week){
        if (week){
            return "Semaine :$week"
        }
        else{
            return ""
        }


    }
    def static getLongMonthName(Integer month){
        switch (month){
            case 1: 'Janvier'
                break
            case 2: 'Fevrier'
                break
            case 3: 'Mars'
                break
            case 4: 'Avril'
                break
            case 5: 'Mai'
                break
            case 6: 'Juin'
                break
            case 7: 'Juillet'
                break
            case 8: 'Août'
                break
            case 9: 'Septembre'
                break
            case 10: 'Octobre'
                break
            case 11: 'Novembre'
                break
            case 12: 'Decembre'
                break
            default:''
                break;

        }
    }
}
