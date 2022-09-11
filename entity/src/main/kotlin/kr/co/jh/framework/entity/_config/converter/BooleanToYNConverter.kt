package kr.co.jh.framework.entity._config.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter


/**
 * Class Description
 * DB data 'Y/N' 과 객체 boolean 컨버터
 * 글로벌 설정
 *
 * @author 박지환
 * @since 2022-09-11
 */
@Converter(autoApply = true)
class BooleanToYNConverter : AttributeConverter<Boolean?, Char?> {
    override fun convertToDatabaseColumn(attribute: Boolean?): Char {
        return if (attribute != null && attribute) 'Y' else 'N'
    }

    override fun convertToEntityAttribute(dbData: Char?): Boolean {
        return if (dbData == null) {
            false
        } else dbData == 'Y'
    }
}
