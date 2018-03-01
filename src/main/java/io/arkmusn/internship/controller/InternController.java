package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.Intern;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.model.vo.InternListVo;
import io.arkmusn.internship.service.InternService;
import io.arkmusn.internship.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Arkmusn
 *         create 2017/12/11
 */

@Controller
@RequestMapping("intern")
public class InternController extends BaseController {
    private InternService internService;

    @Autowired
    public InternController(InternService internService) {
        this.internService = internService;
    }

    /**
     * 获取实习申报书信息
     *
     * @param id 实习申报书ID
     * @return 实习申报书
     */
    @RequestMapping(value = "{id}",
                    method = RequestMethod.GET)
    public @ResponseBody
    Response<Intern> get(@PathVariable Integer id) {
        return new Response<>(internService.get(id));
    }

    /**
     * 获取实习申报书列表
     *
     * @param internListVo 学生信息分页对象
     * @return 分页实习申报书信息
     */
    @RequestMapping(value = "/",
                    method = RequestMethod.GET)
    public @ResponseBody
    Response<Page<Intern>> list(InternListVo internListVo) {
        return new Response<>(internService.list(PageUtils.toPageable(internListVo)));
    }

    /**
     * 编辑申报书
     *
     * @param intern 申报书
     * @return 结果
     */
    @RequestMapping(value = "/",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Intern intern) {
        return new Response(internService.save(intern));
    }

    /**
     * 删除申报书
     *
     * @param ids 申报书ID列表
     * @return 结果
     */
    @RequestMapping(value = "delete",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response<Integer> delete(@RequestBody Collection<Integer> ids) {
        return new Response<>(true, internService.delete(ids));
    }

    /**
     * 结束实习
     *
     * @param ids 申报书ID列表
     * @return 结果
     */
    @RequestMapping(value = "finish",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response<Integer> finish(@RequestBody Collection<Integer> ids) {
        return new Response<>(true, internService.finish(ids));
    }

    /**
     * 申报书审核通过
     *
     * @param ids 申报书ID列表
     * @return 结果
     */
    @RequestMapping(value = "audit",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response audit(@RequestBody Collection<Integer> ids) {
        return new Response<>(true, internService.audit(ids));
    }

    /**
     * 申报书审核打回
     *
     * @param ids 申报书ID列表
     * @return 结果
     */
    @RequestMapping(value = "reject",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response reject(@RequestBody Collection<Integer> ids) {
        return new Response<>(true, internService.reject(ids));
    }
}
