package com.dev.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.dev.beans.Asset;
import com.dev.beans.AssetAllocation;
import com.dev.beans.Employee;
import com.dev.beans.UserMaster;
import com.dev.exceptions.AddAssetException;
import com.dev.exceptions.AddEmployeeException;
import com.dev.exceptions.GetAssetException;
import com.dev.exceptions.LoginException;
import com.dev.exceptions.RaiseAllocationException;
import com.dev.exceptions.RemoveAssetException;
import com.dev.exceptions.StatusException;
import com.dev.exceptions.UpdateAssetException;
import com.dev.exceptions.ValidationException;
import com.dev.repo.Database;
import com.util.validations.Validate;

public class DAOImpl implements DAO {
	Database database = new Database();

	@Override
	public UserMaster login(Integer userid, String password) {
		try {
		if (database.map.containsKey(userid)) {
			UserMaster um = database.map.get(userid);
			if (um.getUserpassword().equals(password)) {
				return um;
			}
		}
		throw new LoginException();
	}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Asset addAsset(Asset asset) {

		try {
			if (!database.mapa.containsKey(asset.getAssetid())) {
				database.mapa.put(asset.getAssetid(), asset);
				return asset;
			} else {
				throw new AddAssetException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Asset removeAsset(Integer aid) {
		try {
			Asset a = new Asset();
			if (database.mapa.containsKey(aid)) {
				a = database.mapa.remove(aid);
				return a;
			} else {

				throw new RemoveAssetException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Asset> getAllAsset() {
		try {
			if (!database.mapa.isEmpty()) {
				List<Asset> list = new ArrayList(database.mapa.values());
				Iterator<Asset> it = list.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}

				return list;
			} else {
				throw new GetAssetException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		try {
			if (!database.mape.containsKey(employee.getEmpno())) {
				if (database.mapd.containsKey(employee.getDeptid())) {
					database.mape.put(employee.getEmpno(), employee);
					return employee;
				}

				throw new AddEmployeeException();
			} else {

				throw new AddEmployeeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AssetAllocation raiseAllocation(AssetAllocation assetallocation) {
		try {
			if (!database.mapas.containsKey(assetallocation.getAllocationid())) {
				database.mapas.put(assetallocation.getAllocationid(), "null");
				if (!database.mapaa.containsKey(assetallocation.getAllocationid())) {
					database.mapaa.put(assetallocation.getAllocationid(), assetallocation);
					return assetallocation;
				}
			} else {
				throw new RaiseAllocationException();
			}
			throw new RaiseAllocationException();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AssetAllocation> getAllAssetAllocation() {
		try {
			if (!database.mapaa.isEmpty()) {
				List<AssetAllocation> list = new ArrayList(database.mapaa.values());
				Iterator<AssetAllocation> it = list.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}
				return list;

			} else {
				System.out.println("raise list is not available");
				throw new RaiseAllocationException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean setStatus(Integer allocationid, String status) {
		try {
			if (database.mapaa.containsKey(allocationid)) {
				database.mapas.put(allocationid, status);
				return true;

			} else {
				throw new StatusException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String viewStatus(Integer allocationid) {
		try {
			if (database.mapas.containsKey(allocationid)) {

				String s = database.mapas.get(allocationid);

				if (!s.isEmpty()) {
					return s;
				} else {

					throw new StatusException();
				}

			} 
				throw new StatusException();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "status not available";
	}

	@Override
	public Asset updateAssetName(Integer aid, String assetname) {
		try {
			if (database.mapa.containsKey(aid)) {
				Asset s = database.mapa.get(aid);
				s.setAssetname(assetname);
				return s;
			} else {
				throw new UpdateAssetException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Asset updateAssetDes(Integer aid, String assetdes) {
		try {
			if (database.mapa.containsKey(aid)) {
				Asset s = database.mapa.get(aid);
				s.setAssetdes(assetdes);
				return s;
			} else {
				throw new UpdateAssetException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Asset updateAssetQuantity(Integer aid, Integer assetquan) {
		try {
			if (database.mapa.containsKey(aid)) {
				Asset s2 = database.mapa.get(aid);
				s2.setQuantity(assetquan);
				return s2;
			} else {
				throw new UpdateAssetException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Asset updateAssetStatus(Integer aid, String assetstatus) {
		try {
			if (database.mapa.containsKey(aid)) {
				Asset s = database.mapa.get(aid);
				s.setStatus(assetstatus);
				return s;
			} else {
				throw new UpdateAssetException();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
