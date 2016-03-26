package simagriclasses

import org.springframework.beans.PropertyEditorRegistry
import java.text.SimpleDateFormat
/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 14/11/12
 * Time: 01:57
 * To change this template use File | Settings | File Templates.
 */
class CustomDateEditorRegistrar {
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy hh:mm a"), true))
    }
}
