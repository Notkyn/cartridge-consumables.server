package ua.notky.cartridge.consumables.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class RootController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping(value = "/departments")
    public String departments(){
        return "pages/departments";
    }

    @GetMapping(value = "/cartridges")
    public String cartridges(){
        return "pages/cartridges";
    }

    @GetMapping(value = "/cleaning_blades")
    public String cleaningBlades(){
        return "pages/cleaning_blades";
    }

    @GetMapping(value = "/dispensing_blades")
    public String dispensingBlades(){
        return "pages/dispensing_blades";
    }

    @GetMapping(value = "/drums")
    public String drums(){
        return "pages/drums";
    }

    @GetMapping(value = "/magnetic_shafts")
    public String magneticShafts(){
        return "pages/magnetic_shafts";
    }

    @GetMapping(value = "/primary_charge_shafts")
    public String primaryChargeShafts(){
        return "pages/primary_charge_shafts";
    }

    @GetMapping(value = "/toners")
    public String toners(){
        return "pages/toners";
    }

    @GetMapping(value = "/tools")
    public String tools(){
        return "pages/tools";
    }
}
