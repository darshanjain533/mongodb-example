package com.tata.croma.vas.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection="Vas")
public class Vas {
	@Id
    String id;
	@Indexed(unique=true)
	Integer vas_master_id;
	Integer sku_id;
	Integer service_product_l1;
	Integer service_product_l2;
	Integer coverage_period;
	Integer l0_category_code;
	Integer l1_category_code;
	List<Integer> l2_category_code;
	Integer approval_status;
	Integer channel_type;
	Double selling_price;
	Double mrp;
	Double min_coverage_price;
	Double max_coverage_price;
	@JsonFormat(pattern="dd-MM-yyyy")
	@CreatedDate
	Date created_on;
	@JsonFormat(pattern="dd-MM-yyyy")
	@LastModifiedDate
	Date last_modified_at;
	String service_description_original;
	String service_description_short;
	String brand_code;
	String brand_name;
	String material_type;
	String offered_by;
	

}
