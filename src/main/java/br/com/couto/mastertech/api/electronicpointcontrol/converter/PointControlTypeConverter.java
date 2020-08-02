package br.com.couto.mastertech.api.electronicpointcontrol.converter;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.couto.mastertech.model.TipoMarcacao;

@Converter(autoApply = true)
public class PointControlTypeConverter implements AttributeConverter<TipoMarcacao, String> {
  
    @Override
    public String convertToDatabaseColumn(TipoMarcacao pointRecordType) {
        if (pointRecordType == null) {
            return null;
        }
        return pointRecordType.getType();
    }
 
    @Override
    public TipoMarcacao convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        
        return Stream.of(TipoMarcacao.values())
          .filter(c -> c.getType().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}