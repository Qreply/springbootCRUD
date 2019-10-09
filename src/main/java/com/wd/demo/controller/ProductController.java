package com.wd.demo.controller;

import com.wd.demo.db2.service.IProductService;
import com.wd.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    IProductService productService;


    /**
     * 商品列表
     * @param model
     * @return
     */
    @RequestMapping("/productList")
    public String productList(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "product/productList";
    }

    /**
     * 商品列表点击Add
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "product/productAdd";
    }

    /**
     * 执行增加商品信息，增加提交后自动返回商品列表界面
     * @param product
     * @return
     */
    @RequestMapping("/add")
    public String add(Product product){
        productService.saveProduct(product);
        return "redirect:/productList";
    }

    /**
     * 商品列表点击update
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Model model, Integer id){
        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        return "product/productUpdate";
    }

    /**
     * 执行更新商品信息，增加提交后自动返回商品列表界面
     * @param product
     * @return
     */
    @RequestMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/productList";
    }

    /**
     * 商品列表点击delete
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        productService.delete(id);
        return "redirect:/productList";
    }

}
