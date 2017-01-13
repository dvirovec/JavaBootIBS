package inforbis.erp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Controller
public class PageController {
    @RequestMapping("/erp")
    public String HomePage() {
        return "index";
    }
}
