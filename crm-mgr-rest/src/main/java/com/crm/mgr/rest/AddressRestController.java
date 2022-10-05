package com.crm.mgr.rest;

import com.crm.mgr.dto.AddressDto;
import com.crm.mgr.service.impl.AddressService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address")
@CrossOrigin()
public class AddressRestController {
    private final AddressService addressService;

    public AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public AddressDto createAddress(@Valid @RequestBody AddressDto dto) {
        return addressService.createAddress(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public AddressDto deleteAddressById(@PathVariable UUID id) {
        return addressService.deleteAddressById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public AddressDto modifyAddress(@Valid @RequestBody AddressDto dto, @PathVariable UUID id) {
        return addressService.modifyAddress(dto, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<AddressDto> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public AddressDto getAddressById(@PathVariable UUID id) {
        return addressService.getAddressById(id);
    }
}
