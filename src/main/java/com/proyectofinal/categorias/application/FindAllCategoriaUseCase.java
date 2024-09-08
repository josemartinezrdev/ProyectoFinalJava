package com.proyectofinal.categorias.application;

import java.util.List;

import com.proyectofinal.categorias.domain.entity.Categoria;
import com.proyectofinal.categorias.domain.service.CategoriaService;

public class FindAllCategoriaUseCase {
    private final CategoriaService categoriaService;

    public FindAllCategoriaUseCase(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public List<Categoria> execute() {
        return categoriaService.findAllCategorias();
    }

}
