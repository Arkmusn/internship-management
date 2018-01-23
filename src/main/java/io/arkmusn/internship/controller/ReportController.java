package io.arkmusn.internship.controller;

import io.arkmusn.internship.domain.entity.Report;
import io.arkmusn.internship.model.bo.Response;
import io.arkmusn.internship.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Arkmusn
 *         create 2017/12/21
 */

@Controller
@RequestMapping("report")
public class ReportController {
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 获取实习周报信息
     *
     * @param id 实习周报ID
     * @return 实习周报信息
     */
    @RequestMapping(value = "/{id}",
                    method = RequestMethod.GET)
    public @ResponseBody
    Response<Report> get(@PathVariable Integer id) {
        return new Response<>(reportService.get(id));
    }

    /**
     * 编辑实习周报
     *
     * @param report 实习周报
     * @return 结果
     */
    @RequestMapping(value = "/",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response edit(@RequestBody Report report) {
        return new Response(reportService.save(report));
    }

    /**
     * 删除实习周报
     *
     * @param ids 实习周报ID列表
     * @return 结果
     */
    @RequestMapping(value = "/delete",
                    method = RequestMethod.POST)
    public @ResponseBody
    Response<Integer> delete(@RequestBody Collection<Integer> ids) {
        return new Response<>(reportService.delete(ids));
    }
}
