package hello.thymeleaf.basic.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailComponentWithBean {

    private final TemplateEngine emailTemplateEngine;

    public String makeEmailBody(String prefix, String input) {
        Context context = new Context();
        context.setVariable("input", input);

        return getHtml(prefix + "email", context);
    }

    private String getHtml(String template, Context context) {
        return emailTemplateEngine.process(template, context);
    }

}
