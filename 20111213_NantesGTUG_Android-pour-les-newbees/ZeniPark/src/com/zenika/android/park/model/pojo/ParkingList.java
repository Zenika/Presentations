package com.zenika.android.park.model.pojo;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class ParkingList {

	@ElementList(name = "Groupes_Parking", required = true)
	@Path("answer/data")
	private List<Parking>	mList;

	public List<Parking> getParkings() {
		return mList;
	}
}
