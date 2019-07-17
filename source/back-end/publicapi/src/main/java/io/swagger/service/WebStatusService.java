package io.swagger.service;

import io.swagger.model.Pagination;
import io.swagger.pojo.dao.Status;
import io.swagger.pojo.dao.User;
import io.swagger.pojo.dao.repos.UserRepository;
import io.swagger.pojo.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WebStatusService {

    Status add(Status status, Long createBy);

    int deleteByProblemId(Long problemId);

    Status update(Status status, Long updateBy);


}
