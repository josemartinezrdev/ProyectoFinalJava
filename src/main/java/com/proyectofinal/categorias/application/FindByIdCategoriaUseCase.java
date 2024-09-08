package com.proyectofinal.categorias.application;

import com.proyectofinal.categorias.domain.entity.Categoria;
import com.proyectofinal.categorias.domain.service.CategoriaService;

public class FindByIdCategoriaUseCase {
    private final CategoriaService categoriaService;

    public FindByIdCategoriaUseCase(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Categoria execute(int id) {
        return categoriaService.findByIdCategoria(id);
    }
}
