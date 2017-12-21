package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.Intern;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Controller
@RequestMapping("intern")
public class InternController extends BaseController {
    @Autowired
    private InternService internService;

    /**
     * 获取实习申报书信息
     *
     * @param id 实习申报书ID
     * @return 实习申报书
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response<Intern> get(@PathVariable Integer id) {
        return new Response<>(internService.get(id));
    }

    /**
     * 编辑申报书
     *
     * @param intern 申报书
     * @return 结果
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Intern intern) {
        return new Response(internService.edit(intern));
    }
}
