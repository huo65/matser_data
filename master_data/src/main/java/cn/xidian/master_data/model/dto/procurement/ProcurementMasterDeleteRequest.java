package cn.xidian.master_data.model.dto.procurement;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.List;


/**
 * @author huozj
 */
@Data
public class ProcurementMasterDeleteRequest implements Serializable {

    private List<Integer> ids;

}