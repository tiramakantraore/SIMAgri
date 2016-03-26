package simagriclasses

import static org.h2.util.MathUtils.setScale

/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 10/12/12
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
class Rounding {
    public BigDecimal round(int n) {
        return setScale(n, BigDecimal.ROUND_HALF_UP);
    }
}
