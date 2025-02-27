package diary.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String encryptKey;

    @Bean(name = "jasyptEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(encryptKey); // Jasypt 비밀번호
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000"); // 키 획득 반복 횟수 (기본값: 1000)
        config.setPoolSize("1"); // 암호화 풀 크기
        config.setProviderName("SunJCE"); // 기본 Java 암호화 제공자 사용
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // 랜덤 Salt 사용
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator"); // 랜덤 IV 사용 (AES-256을 위해 필수)
        config.setStringOutputType("base64"); // Base64 형식으로 암호화된 값 저장

        encryptor.setConfig(config);

        return encryptor;
    }
}
