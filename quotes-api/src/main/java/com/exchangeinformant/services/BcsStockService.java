package com.exchangeinformant.services;

import com.exchangeinformant.configuration.BcsConfig;
import com.exchangeinformant.dto.InfoDTO;
import com.exchangeinformant.repository.NameRepositoryRedis;
import com.exchangeinformant.util.Name;
import com.exchangeinformant.util.Root;
import com.exchangeinformant.dto.StockDTO;
import com.exchangeinformant.exception.ErrorCodes;
import com.exchangeinformant.exception.QuotesException;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import com.exchangeinformant.util.Bcs;
import com.exchangeinformant.util.StockClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import ru.tinkoff.invest.openapi.OpenApi;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 05.01.2023
 * Time: 17:27
 */
@Service
@Bcs
public class BcsStockService implements StockService {

    private final WebClient webClient;
    private final BcsConfig bcsConfig;
    private final InfoRepository infoRepository;
    private final StockRepository stockRepository;
    private final NameRepositoryRedis nameRepository;
    private final StockClient stockClient;

    public BcsStockService(WebClient webClient, BcsConfig bcsConfig, InfoRepository infoRepository, StockRepository stockRepository, NameRepositoryRedis nameRepository, StockClient stockClient) {
        this.webClient = webClient;
        this.bcsConfig = bcsConfig;
        this.infoRepository = infoRepository;
        this.stockRepository = stockRepository;
        this.nameRepository = nameRepository;
        this.stockClient = stockClient;
    }
    @Scheduled(cron = "0 */2 * * * *")
    @Override
    public void updateAllStocks() {
        if(nameRepository.findAll().isEmpty()){
            getAllStocks();
        }
        List<Stock> allStocks = stockRepository.findAll();
        for (Stock stock : allStocks) {
            try {
//                Mono<List<StockDTO>> mono = webClient
//                        .get()
//                        .uri(bcsConfig.getUrl() + String.format(bcsConfig.getOneStock(), stock.getSecureCode()))
//                        .retrieve()
//                        .bodyToMono(new ParameterizedTypeReference<>() {});
                List<StockDTO> foundStock = stockClient.findOneStock(stock.getSecureCode());
                StockDTO stockDTO = Objects.requireNonNull(foundStock).get(0);
                stockRepository.save(new Stock(stockDTO.getSecureCode(),stockDTO.getIssuer(), stockDTO.getCurrency()));
                InfoDTO infoDTO = stockDTO.getInfoList();
                Info info = convertInfoDTOToInfo(infoDTO);
                info.setSecureCode(stock.getSecureCode());
                infoRepository.save(info);
            } catch (WebClientRequestException e) {
                e.printStackTrace();
                throw new QuotesException(ErrorCodes.UPDATE_PROBLEM.name());
            }
        }
        System.out.printf("%s: Updated Successfully%n", LocalDateTime.now());
    }

    @Override
    public void getAllStocks() {
        try {
//            List<Name> mono =  webClient
//                    .get()
//                    .uri(bcsConfig.getUrl() + bcsConfig.getAllStocks())
//                    .header("partner-token", bcsConfig.getPartnerToken())
//                    .accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .bodyToMono(new ParameterizedTypeReference<Root>(){})
//                    .block()
//                    .getName();
            List<Name> names = stockClient.findAllStocks().getName();
            for (Name name : names) {
                nameRepository.save(name);
            }
            saveAllStocks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveAllStocks() {
        nameRepository.findAll()
                .forEach(n -> stockRepository.save(new Stock(n.getSecureCode(),n.getIssuer(),n.getCurrency())));
    }


//    @Override
//    public List<Stock> getStocksByCodes(List<String> codes) {
//        List<Stock> result = new ArrayList<>();
//        for(String code : codes){
//            result.add(stockRepository.findBySecureCode(code));
//        }
//        return result;
//    }

    private Info convertInfoDTOToInfo(InfoDTO infoDTO) {
        Info info = new Info();
        info.setUpdatedAt(LocalDateTime.now());
        info.setLastPrice(infoDTO.getLastPrice());
        return info;
    }






}
