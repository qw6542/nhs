package Hello;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class FormController {
    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ResponseBody
    public String handlePost(HttpServletRequest request,
                             @RequestParam("name") String name,
                             @RequestParam("frequency") String frequency,
                             @RequestParam("income") String income,
                             @RequestParam("outgoing") String outgoing
    ) {
        return (name + frequency + income + outgoing);
    }

}