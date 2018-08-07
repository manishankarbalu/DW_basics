package health;

import com.codahale.metrics.health.HealthCheck;

public class healthCheck extends HealthCheck {

    private final String greeting;

    public healthCheck(String greeting) {
        this.greeting = greeting;
    }
    @Override
    protected Result check() throws Exception {
        final String saying = String.format(greeting, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
