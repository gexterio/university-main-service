package ua.com.foxminded.university.consumer.dto;

import java.util.Objects;

public class TransactionDTO {

    private Long id;

    private Double count;

    private String currencyName;

    private Long customerId;

    private Double usdCount;

    public TransactionDTO() {
    }

    public TransactionDTO (Builder builder) {
        this.id=builder.id;
        this.count=builder.count;
        this.currencyName=builder.currencyName;
        this.customerId= builder.customerId;
        this.usdCount= builder.usdCount;
    }

    private static class Builder {

        private Long id;

        private Double count;

        private String currencyName;

        private Long customerId;

        private Double usdCount;

        public Builder id (Long id) {
            this.id = id;
            return this;
        }

        public Builder count (Double count) {
            this.count=count;
            return this;
        }

        public Builder currencyName(String currencyName) {
            this.currencyName = currencyName;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId=customerId;
            return this;
        }

        public Builder usdCount (Double usdCount) {
            this.usdCount=usdCount;
            return this;
        }

        public TransactionDTO build (){
            return new TransactionDTO(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getUsdCount() {
        return usdCount;
    }

    public void setUsdCount(Double usdCount) {
        this.usdCount = usdCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(count, that.count) && Objects.equals(currencyName, that.currencyName) && Objects.equals(customerId, that.customerId) && Objects.equals(usdCount, that.usdCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, currencyName, customerId, usdCount);
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", count=" + count +
                ", currencyName='" + currencyName + '\'' +
                ", customerId=" + customerId +
                ", usdCount=" + usdCount +
                '}';
    }

}
