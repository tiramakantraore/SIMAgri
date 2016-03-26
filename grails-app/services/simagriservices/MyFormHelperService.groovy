package simagriservices

import grails.transaction.Transactional

@Transactional(readOnly=false)
class MyFormHelperService {

    String getCheckBoxTemplate(sizeOfList){
        switch(sizeOfList) {
            case 0..10:
                return "my1Columns"
                break
            case 10..20:
                return "my2Columns"
                break
            case 20..30:
                return "my3Columns"
                break
            case 30..40:
                return "my4Columns"
                break
            case 40..50:
                return "my5Columns"
                break
            case 50..60:
                return "my6Columns"
                break
            default:
                return "my6Columns"
                break

        }

    }
}
