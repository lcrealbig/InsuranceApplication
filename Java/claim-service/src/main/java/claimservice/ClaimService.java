package claimservice;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClaimService {
    @Autowired
    EurekaClient eurekaClient;

}
