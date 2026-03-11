import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyPropRunner implements CommandLineRunner {

    // application.properties의 값을 가져옵니다
    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Username: " + username);
        System.out.println("Random Port: " + port);
    }
}