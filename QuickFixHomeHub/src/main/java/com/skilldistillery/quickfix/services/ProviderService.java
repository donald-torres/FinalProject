package com.skilldistillery.quickfix.services;

import com.skilldistillery.quickfix.entities.Provider;

public interface ProviderService {


	public Provider show(int id, String username);
	public Provider create(String username, Provider provider);
	public Provider update(String username, int id, Provider provider);
	public boolean destroy(String username, int id);

}
