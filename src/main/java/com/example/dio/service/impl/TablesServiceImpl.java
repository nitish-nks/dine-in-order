package com.example.dio.service.imp;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.TableMapper;
import com.example.dio.model.Restaurant;
import com.example.dio.model.Tables;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.TablesRepository;
import com.example.dio.service.TablesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TablesServiceImpl implements TablesService {
    private TablesRepository tablesRepository;
    private RestaurantRepository restaurantRepository;
    private TableMapper tableMapper;

    @Override
    public TableResponse registerTable(TableRequest tableRequest, Restaurant restaurantId) {
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId.getRestaurantId())
                .orElseThrow(() -> new UserNotFoundByIdException("Restaurant Not Found by id"));

        Tables tables = tableMapper.mapToTableEntity(tableRequest);
        tables.setRestaurant(foundRestaurant);

        tablesRepository.save(tables);
        return tableMapper.mapToTableResponse(tables);

    }

}
