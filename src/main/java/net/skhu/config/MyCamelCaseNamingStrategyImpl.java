package net.skhu.config;

import java.io.Serializable;
import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class MyCamelCaseNamingStrategyImpl implements PhysicalNamingStrategy, Serializable {

    @Override
    public Identifier toPhysicalCatalogName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return apply( logicalName, jdbcEnvironment );
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return apply( logicalName, jdbcEnvironment );
    }

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return apply( logicalName, jdbcEnvironment );
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return apply( logicalName, jdbcEnvironment );
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        return apply( logicalName, jdbcEnvironment );
    }

    private Identifier apply(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
        if ( name == null ) {
            return null;
        }
        StringBuilder builder = new StringBuilder( name.getText().replace( '.', '_' ) );
        for ( int i = 0; i < builder.length() - 1; i++ ) {
            char ch = builder.charAt(i);
            if ('a' <= ch && ch <= 'z') break;
            if ('A' <= ch && ch <= 'Z') {
                builder.setCharAt(i, Character.toLowerCase(ch));
                break;
            }
        }
        return getIdentifier( builder.toString(), name.isQuoted(), jdbcEnvironment );
    }

    protected Identifier getIdentifier(String name, final boolean quoted, final JdbcEnvironment jdbcEnvironment) {
        if ( isCaseInsensitive( jdbcEnvironment ) ) {
            name = name.toLowerCase( Locale.ROOT );
        }
        return new Identifier( name, quoted );
    }

    protected boolean isCaseInsensitive(JdbcEnvironment jdbcEnvironment) {
        return false;
    }
}