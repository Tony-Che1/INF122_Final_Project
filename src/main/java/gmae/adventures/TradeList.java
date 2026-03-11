package gmae.adventures;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TradeList {

    private final String realmId;
    private final Map<String, Integer> buyPrices;
    private final Map<String, Integer> sellPrices;

    public TradeList(String realmId, Map<String, Integer> buyPrices, Map<String, Integer> sellPrices) {
        if (realmId == null || realmId.isBlank()) {
            throw new IllegalArgumentException("Realm id must not be blank");
        }
        if (buyPrices == null || buyPrices.isEmpty()) {
            throw new IllegalArgumentException("Buy prices must not be empty");
        }
        if (sellPrices == null || sellPrices.isEmpty()) {
            throw new IllegalArgumentException("Sell prices must not be empty");
        }

        this.realmId = realmId;
        this.buyPrices = Collections.unmodifiableMap(new LinkedHashMap<>(buyPrices));
        this.sellPrices = Collections.unmodifiableMap(new LinkedHashMap<>(sellPrices));
    }

    public String getRealmId() {
        return realmId;
    }

    public Set<String> getItemNames() {
        return buyPrices.keySet();
    }

    public boolean canBuy(String itemName) {
        return buyPrices.containsKey(itemName);
    }

    public int getBuyPrice(String itemName) {
        return buyPrices.getOrDefault(itemName, -1);
    }

    public int getSellPrice(String itemName) {
        return sellPrices.getOrDefault(itemName, -1);
    }

    public Map<String, Integer> getBuyPrices() {
        return buyPrices;
    }

    public Map<String, Integer> getSellPrices() {
        return sellPrices;
    }

    public String describeMarket() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String itemName : buyPrices.keySet()) {
            if (!first) {
                sb.append(" | ");
            }
            sb.append(itemName)
                    .append(" buy:")
                    .append(getBuyPrice(itemName))
                    .append(" sell:")
                    .append(getSellPrice(itemName));
            first = false;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return describeMarket();
    }
}

