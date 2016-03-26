package simagriclasses

import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor
import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 23/06/12
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
	def dateEditor

	void registerCustomEditors(PropertyEditorRegistry registry) {
       registry.registerCustomEditor(Date.class, dateEditor)
       registry.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor())
	}
}
