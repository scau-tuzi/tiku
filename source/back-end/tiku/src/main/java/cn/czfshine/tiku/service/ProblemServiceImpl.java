package cn.czfshine.tiku.service;

import cn.czfshine.tiku.pojo.dao.Problem;
import cn.czfshine.tiku.pojo.dao.repos.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;
    @Override
    public List<Problem> getAll() {
        return problemRepository.findAll();
    }
}
