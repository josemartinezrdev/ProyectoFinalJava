package com.proyectofinal.categorias.domain.service;

import java.util.List;

import com.proyectofinal.categorias.domain.entity.Categoria;

public interface CategoriaService {
    public void createCategoria(Categoria categoria);

    public void updateCategoria(Categoria categoria);

    public void deleteCategoria(int id);

    public List<Categoria> findAllCategorias();

    public Categoria findByIdCategoria(int id);
}
