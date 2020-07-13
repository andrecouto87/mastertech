package br.com.couto.mastertech.api.electronicpointcontrol.converter;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.couto.mastertech.api.electronicpointcontrol.model.PointRecordType;

@Converter(autoApply = true)
public class PointControlTypeConverter implements AttributeConverter<PointRecordType, String> {
  
    @Override
    public String convertToDatabaseColumn(PointRecordType pointRecordType) {
        if (pointRecordType == null) {
            return null;
        }
        return pointRecordType.getType();
    }
 
    @Override
    public PointRecordType convertToEntityAttribute(String code) { 	
        if (code == null) {
            return null;
        }
        
        return Stream.of(PointRecordType.values())
          .filter(c -> c.getType().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}