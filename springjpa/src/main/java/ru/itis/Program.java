package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.SpringConfig;
import ru.itis.models.Country;
import ru.itis.models.Firm;
import ru.itis.service.CountryService;
import ru.itis.service.FirmService;

/**
 * Created by Иван on 05.03.2017.
 */
public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        CountryService service = context.getBean(CountryService.class);
        FirmService firmService = context.getBean(FirmService.class);
        Country russia = new Country("russia","europa","putin",true);
        service.save(russia);
        Firm firm = new Firm("firm1",null,"ivanov",russia);
        firmService.save(firm);
        firm.setId(3);
        firm.setFirmName("MAZDA");
        firmService.update(firm);

    }
}
