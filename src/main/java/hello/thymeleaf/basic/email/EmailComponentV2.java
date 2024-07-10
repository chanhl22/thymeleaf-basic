package hello.thymeleaf.basic.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailComponentV2 {

    private final TemplateEngine templateEngine;
    private final ApplicationContext applicationContext;

    public String makeEmailBody(String input) {
        Context context = new Context();
        context.setVariable("input", input);

        return getHtml("email", context);
    }

    private String getHtml(String template, Context context) {
        SpringResourceTemplateResolver templateResolver = SpringResourceTemplateResolver();

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(template, context);
    }

    private SpringResourceTemplateResolver SpringResourceTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/email/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(0);
        templateResolver.setApplicationContext(applicationContext);
        return templateResolver;
    }

}
