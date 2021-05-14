package fa.market.distributor



//@RestController
//@RequestMapping("/distributor")
class DistributorRestController {

//    @GetMapping("/hello.json")
    fun hello(): DistributorHello {
        return DistributorHello()
    }
}

