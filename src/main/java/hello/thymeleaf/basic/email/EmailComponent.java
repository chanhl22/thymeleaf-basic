package hello.thymeleaf.basic.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailComponent {

    private final ApplicationContext applicationContext;

    public String makeEmailBody(String input) {
        Context context = new Context();
        context.setVariable("input", input);

        return getHtml("email", context);
    }

    private String getHtml(String template, Context context) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        ClassLoaderTemplateResolver templateResolver = htmlTemplateResolver();

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(template, context);
    }

    private ClassLoaderTemplateResolver htmlTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/email/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(0);
        return templateResolver;
    }

    public String makeEmailBodyV2(String input) {
        Context context = new Context();
        context.setVariable("input", input);

        return getHtmlV2("email", context);
    }

    private String getHtmlV2(String template, Context context) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

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
