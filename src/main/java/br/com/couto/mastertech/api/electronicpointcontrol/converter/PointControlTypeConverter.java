package br.com.couto.mastertech.api.electronicpointcontrol.converter;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.couto.mastertech.model.TipoBatidaModel;

@Converter(autoApply = true)
public class PointControlTypeConverter implements AttributeConverter<TipoBatidaModel, String> {
  
    @Override
    public String convertToDatabaseColumn(TipoBatidaModel pointRecordType) {
        if (pointRecordType == null) {
            return null;
        }
        return pointRecordType.getType();
    }
 
    @Override
    public TipoBatidaModel convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        
        return Stream.of(TipoBatidaModel.values())
          .filter(c -> c.getType().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}