package com.example.deliveryservice.services;

import com.example.deliveryservice.dto.OrderDto;
import com.example.deliveryservice.entities.Address;
import com.example.deliveryservice.entities.Client;
import com.example.deliveryservice.entities.Order;
import com.example.deliveryservice.entities.Transport;
import com.example.deliveryservice.repositories.ClientRepository;
import com.example.deliveryservice.repositories.OrderRepository;
import com.example.deliveryservice.repositories.TransportRepository;
import com.example.deliveryservice.utils.Geocoding;
import com.google.maps.errors.ApiException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final TransportRepository transportRepository;
    private final AddressService addressService;
    private final ModelMapper mapper = new ModelMapper();
    private final Geocoding geocoding;
    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, TransportRepository transportRepository, AddressService addressService, Geocoding geocoding) {

        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.transportRepository = transportRepository;
        this.addressService = addressService;
        this.geocoding = geocoding;

    }

    @Transactional
    public void save(Order order){
        orderRepository.save(order);
    }

    public List<Order> findByClientId(Integer clientId){
        return orderRepository.findByClientId(clientId);
    }
    public Order findByClientIdAndId(Integer clientId, Integer orderId){
        return orderRepository.findByClientIdAndId(clientId, orderId);
    }
//    public List<Order> findByCourierId(Integer courierId){
//        return orderRepository.findByCourierId(courierId);
//    }
//    public List<Order> findByCourierIdAndId(Integer courierId, Integer orderId){
//        return orderRepository.findByCourierIdAndId(courierId, orderId);
//    }
    public List<Order> findByStatus(String status){
        return orderRepository.findByStatus(status);
    }
    public Order findByStatusAndId(String status, Integer orderId){
        return orderRepository.findByStatusAndId(status, orderId);
    }

    public Order findById(Integer id){
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void createOrder(OrderDto orderDto) throws IOException, InterruptedException, ApiException {
        Order order = mapper.map(orderDto, Order.class);
        Client client = clientRepository.findByEmail(orderDto.getClient());
        System.out.println(orderDto.getClient());
        System.out.println(client);

        Transport transport = transportRepository.findByTransportType(orderDto.getTransport());

        List<Double> initialAddressCoords = geocoding.geocoding(orderDto.getInitialAddress());
        List<Double> destinationAddressCoords = geocoding.geocoding(orderDto.getDestinationAddress());
        Address initialAddressObj = addressService.createAddress(initialAddressCoords.get(1), initialAddressCoords.get(0), orderDto.getInitialAddress());
        Address destinationAddressObj = addressService.createAddress(destinationAddressCoords.get(1), destinationAddressCoords.get(0), orderDto.getDestinationAddress());

        order.setClient(client);
        order.setTransport(transport);
        order.setInitialAddress(initialAddressObj);
        order.setDestinationAddress(destinationAddressObj);

        orderRepository.save(order);
    }

    public List<Order> readAll(){
        return (List<Order>) orderRepository.findAll();
    }

    public Order update(Order order){
        return orderRepository.save(order);
    }

    public void delete(Integer id){
        orderRepository.deleteById(id);
    }
}
