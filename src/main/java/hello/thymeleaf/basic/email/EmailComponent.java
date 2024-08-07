package hello.thymeleaf.basic.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailComponent {

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

}
