package nl.timvandijkhuizen.commerce.paypal.gateway;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import com.cryptomorin.xseries.XMaterial;

import nl.timvandijkhuizen.commerce.Commerce;
import nl.timvandijkhuizen.commerce.base.GatewayClient;
import nl.timvandijkhuizen.commerce.base.GatewayType;
import nl.timvandijkhuizen.commerce.config.sources.GatewayConfig;
import nl.timvandijkhuizen.commerce.elements.Gateway;
import nl.timvandijkhuizen.spigotutils.config.ConfigOption;
import nl.timvandijkhuizen.spigotutils.config.ConfigTypes;
import nl.timvandijkhuizen.spigotutils.config.types.ConfigTypeFile;

public class GatewayPayPal implements GatewayType {

    private ConfigOption<String> configClientId;
    private ConfigOption<String> configClientSecret;
    private ConfigOption<Boolean> configTestMode;
    private ConfigOption<File> configPartialTemplate;
    private ConfigOption<File> configConfirmationTemplate;

    public GatewayPayPal() {
        File pluginRoot = Commerce.getInstance().getDataFolder();
        Pattern[] templatePattern = new Pattern[] { Pattern.compile("^.*\\.html$") };
        ConfigTypeFile typeTemplate = new ConfigTypeFile(pluginRoot, templatePattern);

        configClientId = new ConfigOption<>("clientId", "Client Id", XMaterial.NAME_TAG, ConfigTypes.STRING).setRequired(true);
        configClientSecret = new ConfigOption<>("clientSecret", "Client Secret", XMaterial.TRIPWIRE_HOOK, ConfigTypes.PASSWORD).setRequired(true);
        configTestMode = new ConfigOption<>("testMode", "Test Mode", XMaterial.COMMAND_BLOCK, ConfigTypes.BOOLEAN);
        configPartialTemplate = new ConfigOption<>("partialTemplate", "Partial Template", XMaterial.MAP, typeTemplate);
        configConfirmationTemplate = new ConfigOption<>("confirmationTemplate", "Confirmation Template", XMaterial.MAP, typeTemplate);
    }

    @Override
    public String getHandle() {
        return "paypal";
    }
    
    @Override
    public String getDisplayName() {
        return "PayPal";
    }

    @Override
    public XMaterial getIcon() {
        return XMaterial.LIGHT_BLUE_TERRACOTTA;
    }

    @Override
    public Collection<ConfigOption<?>> getOptions() {
        return Arrays.asList(configClientId, configClientSecret, configTestMode, configPartialTemplate, configConfirmationTemplate);
    }

    @Override
    public GatewayClient createClient(Gateway gateway) {
        GatewayConfig config = gateway.getConfig();
        String clientId = configClientId.getValue(config);
        String clientSecret = configClientSecret.getValue(config);
        boolean testMode = configTestMode.getValue(config);
        File partialTemplate = configPartialTemplate.getValue(config);
        File confirmationTemplate = configPartialTemplate.getValue(config);
        
        return new ClientPayPal(gateway, clientId, clientSecret, testMode, partialTemplate, confirmationTemplate);
    }

}
