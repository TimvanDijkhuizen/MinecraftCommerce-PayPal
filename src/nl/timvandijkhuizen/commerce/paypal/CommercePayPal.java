package nl.timvandijkhuizen.commerce.paypal;

import java.io.File;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import nl.timvandijkhuizen.commerce.CommerceApi;
import nl.timvandijkhuizen.commerce.paypal.gateway.GatewayPayPal;
import nl.timvandijkhuizen.spigotutils.PluginBase;

public class CommercePayPal extends PluginBase {

    @Override
    public void setup() {
        CommerceApi.registerGatewayType(new GatewayPayPal());
        
        // Register template resolver
        ClassLoaderTemplateResolver resourceResolver = new ClassLoaderTemplateResolver(getClassLoader());

        resourceResolver.setCheckExistence(true);
        resourceResolver.setPrefix("templates" + File.separatorChar);
        resourceResolver.setSuffix(".html");
        resourceResolver.setCharacterEncoding("UTF-8");
        resourceResolver.setTemplateMode(TemplateMode.HTML);
        resourceResolver.setOrder(3);

        CommerceApi.registerTemplateResolver(resourceResolver);
    }

}
