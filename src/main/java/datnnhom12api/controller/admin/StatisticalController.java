package datnnhom12api.controller.admin;


import datnnhom12api.dto.*;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.OrderMapper;
import datnnhom12api.response.OrderResponse;
import datnnhom12api.service.OrderService;
import datnnhom12api.service.ProductService;
import datnnhom12api.service.ReturnService;
import datnnhom12api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
public class StatisticalController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReturnService returnService;

    @GetMapping("/statistical/{year}")
    public List<StatisticalMonthDTO> index(@PathVariable("year") Integer year){
        List<StatisticalMonthDTO> list = orderService.statisticalByYear(year);
        return list;
    }

    @GetMapping("/statistical/{month}/{year}")
    public List<StatisticalOrderDTO> order(@PathVariable("month") Integer month, @PathVariable("year") Integer year){
        List<StatisticalOrderDTO> list = orderService.statisticalByOrder(month, year);
        return list;
    }


    //số lượng sản phẩm bán ra
    @GetMapping("/statistical")
    public List<StatisticalProductDTO> order(){
        List<StatisticalProductDTO> list = orderService.statisticalByProduct();
        return list;
    }

    @GetMapping("/statistical/sum/product")
    public SumProductDTO sumProduct(){
        SumProductDTO product = productService.sumProduct();
        return product;
    }

    @GetMapping("/statistical/count/order")
    public SumProductDTO countOrder(){
        SumProductDTO order = orderService.countOrder();
        return order;
    }

    @GetMapping("/statistical/count/customer")
    public SumProductDTO countCustomer(){
        SumProductDTO user = userService.countCustomer();
        return user;
    }

    @GetMapping("/statistical/inventory")
    public List<InventoryDTO> findAllInventory(){
        List<InventoryDTO> inventory = returnService.getAllInventory();
        return inventory;
    }


    //tổng số lượng sản phẩm bán ra
    @GetMapping("/statistical/numberOfProductsSold")
    public SumProductDTO numberOfProductsSold(){
        SumProductDTO order = orderService.numberOfProductsSold();
        return order;
    }




}
