import { useState } from 'react';

export function useFormInput(initialValue) {
    const [value, setValue] = useState(initialValue)
    const handleChange = e => {
        console.log('useFormInput', e);
        setValue(e.target.value)
    }
    return {
        value,
        onChange: handleChange
    }
}

