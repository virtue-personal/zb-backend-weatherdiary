package diary.weather.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import diary.weather.WeatherApplication;
import diary.weather.domain.entity.DateWeatherEntity;
import diary.weather.domain.entity.DiaryEntity;
import diary.weather.repository.DateWeatherRepository;
import diary.weather.repository.DiaryRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final DateWeatherRepository dateWeatherRepository;

    private static final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);


    @Value("${openweathermap.key}")
    private String apikey;

    @Transactional
    @Scheduled(cron = "0 0 * * * *") // 매시간 정각마다 실행
    public void saveWeatherDate() {
        logger.info("날씨 데이터 잘 가져왔는지...");
        dateWeatherRepository.save(getWeatherFromApi());
    }


    // 일기 쓰기
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createDiary(LocalDate date, String text) {
        logger.info("started to create diary");

//        System.out.println(getWeatherString());
        // Api -> DB에 저장된 데이터 가져오기
        DateWeatherEntity dateWeatherEntity = getDateWeather(date);

        /* 설명: 파싱된 데이터 + 일기 값 DB에 저장 */
        DiaryEntity nowDiaryEntity = new DiaryEntity();
        nowDiaryEntity.setDateWeather(dateWeatherEntity);
        nowDiaryEntity.setText(text);

        diaryRepository.save(nowDiaryEntity);

        logger.info("end to create diary");
//        logger.error();
//        logger.warn();
    }


    // 시간마다 날씨 정보 가져오기
    private DateWeatherEntity getWeatherFromApi() {
        // open weather map에서 날씨 데이터 가져오기
        String weatherDate = getWeatherString();
        // 날씨 json 파싱
        Map<String, Object> parsedWeather = parseWeather(weatherDate);
        DateWeatherEntity dateWeatherEntity = new DateWeatherEntity();
        dateWeatherEntity.setDate(LocalDate.now());
        dateWeatherEntity.setWeather(parsedWeather.get("main").toString());
        dateWeatherEntity.setIcon(parsedWeather.get("icon").toString());
        // temp를 섭씨로 변환
        double kelvinTemp = (Double) parsedWeather.get("temp");
        double celsiusTemp = kelvinTemp - 273.15;
        double roundedTemp = Math.round(celsiusTemp * 10.0) / 10.0;
        dateWeatherEntity.setTemperature(roundedTemp);
        return dateWeatherEntity;
    }


    private DateWeatherEntity getDateWeather(LocalDate date) {
        List<DateWeatherEntity> dateWeatherEntityListFromDB = dateWeatherRepository.findAllByDate(date);
        if (dateWeatherEntityListFromDB.size() == 0) {
            // 새로 api에서 날씨 정보를 가져와야함
            return getWeatherFromApi();
        } else {
            return dateWeatherEntityListFromDB.get(0);
        }
    }


    // 단일 날짜 조회
    @Transactional(readOnly = true)
    public List<DiaryEntity> readDiary(LocalDate date) {
        logger.debug("read diary");
//        if (date.isAfter(LocalDate.ofYearDay(3050, 1))) {
//            throw new InvalidDate();
//        }
        return diaryRepository.findAllByDate(date);
    }


    // 범위 날짜 조회
    @Transactional(readOnly = true)
    public List<DiaryEntity> readDiaries(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    // 일기 수정
    public void updateDiary(LocalDate date, String text) {
        DiaryEntity nowDiaryEntity = diaryRepository.getFirstByDate(date);
        nowDiaryEntity.setText(text);
        diaryRepository.save(nowDiaryEntity);
    }

    // 일기 삭제
    public void deleteDiary(LocalDate date) {
        diaryRepository.deleteAllByDate(date);
    }

    private String getWeatherString() {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apikey;

        try {

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return response.toString();

        } catch (Exception e) {
            return "failed to get response";
        }

    }

    private Map<String, Object> parseWeather(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> resultMap = new HashMap<>();
        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp", mainData.get("temp"));

        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);

        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));

        return resultMap;
    }

}
