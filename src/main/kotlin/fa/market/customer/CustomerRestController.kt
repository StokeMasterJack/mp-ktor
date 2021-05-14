package fa.market.customer

//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController


//@RestController
//@RequestMapping("/customer")
class CustomerRestController {

//    @GetMapping("/hello.json")
    fun hello(): CustomerHello {
        return CustomerHello()
    }
}

