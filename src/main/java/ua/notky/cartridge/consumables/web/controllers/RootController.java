package ua.notky.cartridge.consumables.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.notky.cartridge.consumables.util.constant.ConstPath;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

@Controller
@RequestMapping(value = ConstUrl.PAGE_MAIN)
public class RootController {

    @GetMapping
    public String index(){
        return ConstPath.PAGE_MAIN;
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

    @GetMapping(value = ConstUrl.PAGE_DRUM)
    public String drums(){
        return ConstPath.PAGE_DRUM;
    }

    @GetMapping(value = ConstUrl.PAGE_MAGNETIC_SHAFTS)
    public String magneticShafts(){
        return ConstPath.PAGE_MAGNETIC_SHAFT;
    }

    @GetMapping(value = ConstUrl.PRIMARY_CHARGE_SHAFT)
    public String primaryChargeShafts(){
        return ConstPath.PRIMARY_CHARGE_SHAFT;
    }

    @GetMapping(value = ConstUrl.PAGE_TONER)
    public String toners(){
        return ConstPath.PAGE_TONER;
    }

    @GetMapping(value = "/tools")
    public String tools(){
        return "pages/tools";
    }
}
